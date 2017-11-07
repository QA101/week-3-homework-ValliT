
public class AdvancedArrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int mat[][] = { {10, 20, 30, 40, 50, 60, 70, 80, 90},
                {15, 25, 35, 45},
                {27, 29, 37, 48},
                {32, 33, 39, 50, 51, 89},
              };
		int[] array = FindMax2D(mat);
		System.out.println("x: "+array[0]+", y: "+ array[1]);
		
	}
	
	public static int FindMax(int[] input) {
		int max = input[0];
		int index = 0;
		for(int i =0; i< input.length; i++) {
			if(input[i]>max) {
				max = input[i];
				index = i;
			}
		}
		return index;
	}
	
	public static int FindMin(int[] input) {
		int min = input[0];
		int index = 0;
		for(int i =0; i< input.length; i++) {
			if(input[i]<min) {
				min = input[i];
				index = i;
			}
		}
		return index;
	}
	
	public static int[] FindMax2D(int[][] input) {
		int max = input[0][0];
		int[] returnInt = {0,0};
		for(int i =0; i< input.length; i++) {
			for(int j = 0; j<input[i].length; j ++) {
				if(input[i][j]>max) {
					max = input[i][j];
					returnInt[0] = i;
					returnInt[1] = j;
				}
			}
		}
		return returnInt;
	}
	
	public static int[] FindMin2D(int[][] input) {
		int min = input[0][0];
		int[] returnInt = {0,0};
		for(int i =0; i< input.length; i++) {
			for(int j = 0; j<input[i].length; j ++) {
				if(input[i][j]<min) {
					min = input[i][j];
					returnInt[0] = i;
					returnInt[1] = j;
				}
			}
		}
		return returnInt;
	}
	
	public static int[] Reverse(int[] input) {
		int[] output = new int[input.length];
		int end = input.length-1;
		for(int i =0; i < input.length; i++) {
			output[i] = input[end];
			end--;
		}
		return output;
	}

}
