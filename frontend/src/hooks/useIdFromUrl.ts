import { useLocation } from 'react-router-dom';

export function useIdFromUrl() {
    const location = useLocation();
    const searchParams = new URLSearchParams(location.search);
    const id = searchParams.get("id");
    const numericId = id ? parseInt(id, 10) : -1;

    return numericId;
}
