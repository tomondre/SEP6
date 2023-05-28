import axios from "../api/axios";

interface StatisticsPoint {
    x: string;
    y: number;
}

let movies: any = null;

const sortByY = (data: any[]) => {
    function compare(a: any, b:any) {
        if ( a.y > b.y ){
            return -1;
        }
        return 1;
    }

    return data.sort( compare );
}

const sortByX = (data: any[]) => {
    function compare(a: any, b:any) {
        if ( a.x < b.x ){
            return -1;
        }
        return 1;
    }

    return data.sort( compare );
}

const mapToStatisticsPoints = (data: Map<string, number>) => {
    const result: StatisticsPoint[] = [];
    data.forEach((value: number, key: string) => {
        result.push({
            x: key,
            y: value
        });
    });
    return result;
}

const processAvgFieldByYear = (movies: any[], field: string): StatisticsPoint[] => {
    let tempMap = new Map();
    for (let movie of movies) {
        const revenue = movie[field];
        if (revenue == 0)
            continue;
        const year: number = parseInt(movie.releaseDate.split("-")[0]);
        if (!tempMap.has(year)) {
            tempMap.set(year, {
                count: 0,
                sum: 0
            });
        }
        let calcObject = tempMap.get(year);
        calcObject.sum += revenue;
        calcObject.count++;
        tempMap.set(year, calcObject);
    }

    for (const item of tempMap) {
        const obj = item[1];
        const avg = obj.sum / obj.count;
        tempMap.set(item[0], avg);
    }

    return sortByX(mapToStatisticsPoints(tempMap));
};



const processGenreRevenueStatistics = (movies: any[]): StatisticsPoint[] => {
    let tempMap = new Map();
    for (let movie of movies) {
        if (movie.boxOffice == 0)
            continue;
        for (const genre of movie.genres) {
            let genreName = genre.name;
            if (!tempMap.has(genreName)) {
                tempMap.set(genreName, 0)
            }
            let genreSum = tempMap.get(genreName);
            genreSum += movie.boxOffice;
            tempMap.set(genreName, genreSum);
        }
    }

    return sortByY(mapToStatisticsPoints(tempMap));
}

const processMovieLanguages = async (): Promise<StatisticsPoint[]> => {
    let tempMap = new Map<string, number>();
    for (let movie of movies) {
        const language = movie.language;
        if (!tempMap.has(movie.language)) {
            tempMap.set(language, 0);
        }
        let count = tempMap.get(language)!!;
        count++;
        tempMap.set(language, count);
    }

    const result: StatisticsPoint[] = [];
    for (const item of tempMap) {
        result.push({
            x: item[0],
            y: item[1]
        })
    }
    return result;
}
const getGenreRevenueStatistics = async (): Promise<StatisticsPoint[]> => {
    await checkForMovies();
    return processGenreRevenueStatistics(movies);
}

const getAvgRevenueByYear = async (): Promise<StatisticsPoint[]> => {
    await checkForMovies();
    return processAvgFieldByYear(movies, 'boxOffice');
}

const getAvgBudgetByYear = async (): Promise<StatisticsPoint[]> => {
    await checkForMovies();
    return processAvgFieldByYear(movies, 'budget');
}

const getMovieLanguages = async (): Promise<StatisticsPoint[]> => {
    await checkForMovies();
    return processMovieLanguages();
}

const checkForMovies = async (): Promise<void> => {
    if (movies == null) {
        try {
            const response = await axios.get(`/movies`);
            movies = response.data;
        }
        catch (error) {
            console.error('Error fetching movies:', error);
        }
    }
}

const statisticsService = {
    getGenreRevenueStatistics,
    getAvgRevenueByYear,
    getAvgBudgetByYear,
    getMovieLanguages
}

export default statisticsService;
