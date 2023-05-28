import axios from "../api/axios";

interface StatisticsPoint {
    x: string;
    y: number;
}

let movies: any = null;

const sort = (data: any[]) => {
    function compare(a: any, b:any) {
        if ( a.y > b.y ){
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

const processAvgRevenueByYear = (movies: any[]): StatisticsPoint[] => {
    let tempMap = new Map();
    for (let movie of movies) {
        const revenue = movie.boxOffice;
        if (revenue == 0)
            continue;
        //TODO get year - parse date
        const year: number = parseInt(movie.releaseDate.split("-")[0]);
        if (!tempMap.has(year)) {
            tempMap.set(year, 0);
        }
        let revenueSum = tempMap.get(year);
        revenueSum += revenue;
        tempMap.set(year, revenueSum);
    }

    return sort(mapToStatisticsPoints(tempMap));
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

    return sort(mapToStatisticsPoints(tempMap));
}
const getGenreRevenueStatistics = async (): Promise<StatisticsPoint[]> => {
    await checkForMovies();
    return processGenreRevenueStatistics(movies);
}

const getAvgRevenueByYear = async (): Promise<StatisticsPoint[]> => {
    await checkForMovies();
    return processAvgRevenueByYear(movies);
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
    getAvgRevenueByYear
}

export default statisticsService;
