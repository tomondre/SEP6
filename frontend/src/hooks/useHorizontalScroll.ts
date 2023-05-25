import { useRef, useEffect } from "react";

export function useHorizontalScroll() {
    const elRef = useRef();
    useEffect(() => {
        const el: any = elRef.current;
        if (el) {
            const onWheel = (e: any) => {
                if (e.deltaY == 0) return;
                e.preventDefault();
                el.scrollTo({ left: e.deltaY + el.scrollLeft });
            };
            el.addEventListener("wheel", onWheel);
            return () => el.removeEventListener("wheel", onWheel);
        }
    }, []);
    return elRef;
}
