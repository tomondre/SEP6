import axios from "../api/axios";

interface StatisticsPoint {
    x: string;
    y: number;
}

let movies: any = null;

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

    const result: StatisticsPoint[] = [];
    tempMap.forEach((value: number, key: string) => {
        result.push({
            x: key,
            y: value
        });
    });
    function compare(a: any, b:any) {
        if ( a.y > b.y ){
            return -1;
        }
        return 1;
    }

    return result.sort( compare );
}
const getGenreRevenueStatistics = async (): Promise<StatisticsPoint[]> => {
    if (movies == null) {
        try {
            const response = await axios.get(`/movies`);
            movies = response.data;
        }
        catch (error) {
            console.error('Error fetching movies:', error);
            return [];
        }
    }
    return processGenreRevenueStatistics(movies);
}

const statisticsService = {
    getGenreRevenueStatistics
}

export default statisticsService;
