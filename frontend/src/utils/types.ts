
export interface IMovie {
    id: number;
    title: string;
    description?: string;
    posterUrl: string;
    runtime?: number;
    language?: string;
    boxOffice?: number;
    budget?: number;
    status?: string;
    releaseDate?: string;
    genres: { id: number; name: string }[];
    people?: { id: number; name: string; profileImg: string; type: string }[];
    review?: { id: number; rating: number; comment: string; createdOn: string; movieId: number; accountId: number }[];
    rating?: number
}

export interface IReview {
    id: number;
    user?: string;
    comment: string;
    rating: number;
    createdOn: string;
    movieTitle: string;
    movieId: number;
    accountId: number;
}

export interface IPerson {
    id: number;
    name: string;
    type: string;
    dateOfBirth: string;
    placeOfBirth: string;
    gender: string;
    biography: string;
    deathDate: string;
    profileImg: string;
    ratingAverage: number;
    movies: IMovie[]
}