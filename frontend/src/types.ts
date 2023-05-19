export interface Movie {
    id: number;
    genres: { id: number; name: string }[];
    posterUrl: string;
    title: string;
}
