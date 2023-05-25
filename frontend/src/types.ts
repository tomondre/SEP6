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