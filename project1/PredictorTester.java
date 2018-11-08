//Hassan M. Khan
//I.D: 001168658
//JAVA

package project1;

import java.util.ArrayList;

public class PredictorTester {

	public static void main(String[] args) 
	{
		//Making a predictor object and passing in a file name
		Predictor justchecking = new Predictor("D:\\Uni Work Doe\\Software Development-JAVA\\Projects\\Project 1\\Project 1\\project1\\carTrain.DATA");

		//Returning the trail data from the predictor class to check
		//whats up.
		ArrayList<Car> jayleno = new ArrayList<Car>(justchecking.gettrainingdata());

		//Printing the cars imported first, to check if all is well.
		for (int i=0; i<jayleno.size(); i++)
		{
			//if (jayleno.get(i).getRating().equals("vgood"))
			System.out.println(jayleno.get(i));
		}

		System.out.println(jayleno.size());

		//Getting the training accuracy, and printing it.
		System.out.println(justchecking.getTrainingAccuracy());


	}
}
