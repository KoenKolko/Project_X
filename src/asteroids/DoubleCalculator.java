package asteroids;

public class DoubleCalculator {
	
	public Double addDoubles(double x, double y) throws ArithmeticException{
		double sum = x + y;
		if(x>0 && y>0){
			if(sum > 0){
				return sum;
			}
			else{
				throw new ArithmeticException();
			}
		}
		else if(x<0 && y<0){
			if(sum < 0){
				return sum;
			}
			else{
				throw new ArithmeticException();
			}
			
		}
		else if(x==0){
			return y;
		}
		else if(y==0){
			return x;
		}		
		else{
			return sum;
		}
	}
	
	public Double multiplyDoubles(double x, double y) throws ArithmeticException{
		double product = x * y;
		if((x>0 && y>0) || (x<0 && y<0)){
			if(product > 0){
				return product;
			}
			else{
				throw new ArithmeticException();
			}				
		}
		else if((x>0 && y<0) || (x<0 && y>0)){
			if(product < 0){
				return product;
			}
			else{
				throw new ArithmeticException();
			}
		}
		else if(x==0 || y == 0){
			return 0.0;
		}
		else{
			return product;
		}
	}
}
