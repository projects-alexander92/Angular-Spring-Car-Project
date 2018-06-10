export interface ICarDetails {
  engine: string;
  id: number;
  image: string;
  make: string;
  model: string;
  price: number;
  username: string;
  year: number;
  reviews: Array<IReview>;
}

interface IReview {
  id: number;
  data: string;
  username: string;
}

