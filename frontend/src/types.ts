export interface IMovie {
    id: number;
    genres: { id: number; name: string }[];
    posterUrl: string;
    title: string;
}

export interface IReview {
    id: number;
    user: string;
    comment: string;
    rating: number;
    date: string;
    movieTitle: string;
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
    movies: IMovie[]
}