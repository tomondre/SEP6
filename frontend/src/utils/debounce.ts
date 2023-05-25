type TimerId = ReturnType<typeof setTimeout>;

function debounce<T extends (...args: any[]) => void>(
  func: T,
  delay: number
): (...args: Parameters<T>) => void {
  let timerId: TimerId;

  return function(...args: Parameters<T>): void {
    clearTimeout(timerId);

    timerId = setTimeout(() => {
      func(...args);
    }, delay);
  };
}

export default debounce;
