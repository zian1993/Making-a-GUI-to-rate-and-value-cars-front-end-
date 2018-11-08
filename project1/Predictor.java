//Hassan M. Khan
//I.D: 001168658
//JAVA

package project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeSet;

public class Predictor implements PredictorADT {

	//Implementing some private data types
	private ArrayList<Car> trainingdata;
	private ArrayList<Double> allratings;
	private String file; //This one isnt totally necessary, but oh well.
	private int correct;
	private int total;
	private double finalval;

	//Creating a basic constructor, just in case
	public Predictor(String filename)
	{
		//Initiating the private data field 'file' with the file name, and others.
		file = new String (filename);
		correct = 0;
		total = 0;
		finalval = 0.0;

		//Initiating the private training data arraylist with the arraylist returned from
		//the getdata function of this class.
		trainingdata = new ArrayList<Car>(getdata(file));

		//First calling the learner function, and passing in our training dataset,
		//which we created when we instantiated this class,
		//in order to get the car score ranges for each car rating: acc, good, vgood.
		learner(trainingdata);
	}

	@Override
	public double getTrainingAccuracy() {
		// TODO Auto-generated method stub
		//Resetting the counters
		correct = 0;
		total = 0;
		finalval = 0.0;

		//Getting the prediction for each of the cars in the data set. hmm.
		for (int i=0; i<trainingdata.size(); i++)
		{
			if ((getPrediction(trainingdata.get(i))).equals(trainingdata.get(i).getRating()))
				correct++;
			total++;
		}

		finalval = (double)correct/total;
		return finalval;
	}

	@Override
	public String getPrediction(CarADT instance) {
		// TODO Auto-generated method stub

		Car hmm = new Car();
		double score = 0.0;
		//Type casting instance to a car datatype and saving it to a car variable.
		hmm = (Car)instance;

		//At this point the scores for each car rating is stored in our
		//private arraylist<double> variable, because we called the learner function
		//in our constructor.

		//1. Get score of the car passed in
		score = carScorer(hmm);

		//Now comparing the score to the ranges we have calculated
		if ((score<=allratings.get(1))&&(score>=allratings.get(0)))
			return "acc";

		else if ((score<=allratings.get(3))&&(score>=allratings.get(2)))
			return "good";

		else if ((score<=allratings.get(5))&&(score>=allratings.get(4)))
			return "vgood";

		//else return unacc
		return "unacc";
	}

	//Creating an extra method for reading the file.
	//This might be cleaner than putting all the code in the
	//constructor?

	//Function to read the training data file.
	//Making this one private, so that only the constructor of the
	//class itself can call it, and the user can't mess with it.
	//The function will take in the name of the file that is to be
	//read, will read the file, make a car instance for each of the 
	//training data set, and then finally return an arraylist of cars
	//which will be caught (int the constructor) by the private 
	//datatype of this class, trainingdata.
	//P.S the code to read the file and break down the line according
	//to commas has been taken from code review/stack exchange. I think
	//its pretty awesome.

	private ArrayList<Car> getdata(String a)
	{

		//Declaring the arraylist that is to be returned
		ArrayList<Car> togoaway = new ArrayList<Car>();

		//Declaring a car object, this will be used to add
		//each car into the arraylist
		Car aha;

		int door;
		int person;

		try {
			File f = new File(a);
			Scanner sc = new Scanner(this.getClass().getResourceAsStream(a));

			while(sc.hasNextLine()){
				String line = sc.nextLine();

				//Splitting string according to ',' here.
				//Making an array of strings from the split.
				String[] details = line.split(",");

				//Setting up the new car.

				//If the person datafield is "more", then people count is 5
				//If the door datafield is "5more", then door count is also 5.

				if (details[2].equals("5more"))
					door = 5;
				else 
					door = Integer.parseInt(details[2]);

				if (details[3].equals("more"))
					person = 5;
				else 
					person = Integer.parseInt(details[3]);

				//Setting up the new car
				aha = new Car(details[0], details[1], door, person, 
						details[4], details[5], details[6]);

				//Adding the newly formed car into my car array.
				togoaway.add(aha);
			}
		}catch (Exception e){         
			e.printStackTrace();}

		//Once everything is done, returning my car array.
		return togoaway;
	}

	//Function : score calculator.
	//This function takes in a car, and then calculates the score of the
	//car, based on the properties of the car. For each property of the car,
	//the most desirable value of that property is assigned the highest value,
	//which will be equal to the number of versions of that property. The following
	//values for that property will be given decremented scores.
	//Example: for buying cost, the 'low' value is assigned a value of 4.
	//Therefore, a car with a high buying cost will get a value of 2, 
	//and therefore its score for only the buying cost will be 
	//score/# of properties = 2/4.
	//The final score of the car will be an addition of all its scores for
	//each of its individual properties.

	public double carScorer(Car a)
	{
		double score1 = 0;
		double score2 = 0;
		double score3 = 0;
		double score4 = 0;
		double score5 = 0;
		double score6 = 0;
		double grandtotal = 0;

		//Creating the scoring criteria of the car
		//This will be a bunch of if statements, I do apologize.

		//Buying criteria
		if (a.getBuy().equals("vhigh"))
			score1 = (double)1/4;
		else if (a.getBuy().equals("high"))
			score1 = (double)2/4;
		else if (a.getBuy().equals("med"))
			score1 = (double)3/4;
		else if (a.getBuy().equals("low"))
			score1 = (double)4/4;

		//Maintenance criteria
		if (a.getMaint().equals("vhigh"))
			score2 = (double)1/4;
		else if (a.getMaint().equals("high"))
			score2 = (double)2/4;
		else if (a.getMaint().equals("med"))
			score2 = (double)3/4;
		else if (a.getMaint().equals("low"))
			score2 = (double)4/4;

		//Doors criteria. For this experiment, we will assume that
		//the most number of doors is the best possible scenario.
		//Ofcourse, footballers may not agree with this.
		if (a.getDoors() == 5)
			score3 = (double)4/4;
		else if (a.getDoors() == 4)
			score3 = (double)3/4;
		else if (a.getDoors() == 3)
			score3 = (double)2/4;
		else if (a.getDoors() == 2)
			score3 = (double)1/4;

		//Persons criteria. Most people that can be carried is assumed
		//to be the best score.
		if (a.getPersons() == 5)
			score4 = (double)3/3;
		else if (a.getPersons() == 4)
			score4 = (double)2/3;
		else if (a.getPersons() == 2)
			score4 = (double)1/3;

		//Boot capacity criteria. Biggest boot is assumed to be the best.
		if (a.getBoot().equals("big"))
			score5 = (double)3/3;
		else if (a.getBoot().equals("med"))
			score5 = (double)2/3;
		else if (a.getBoot().equals("small"))
			score5 = (double)1/3;

		//Safety. Hopefully no one argues with this one. Highest safety
		//is assumed to be the best.
		if (a.getSafety().equals("high"))
			score6 = (double)3/3;
		else if (a.getSafety().equals("med"))
			score6 = (double)2/3;
		else if (a.getSafety().equals("low"))
			score6 = (double)1/3;

		grandtotal = score1+score2+score3+score4+score5+score6;

		//Returning the final score.
		return grandtotal;
	}


	//The learner function.
	//This function will take in an array of cars (which will basically be the
	//training data in our case), and it will return a possible range of score for 
	//the acc, good and vgood ratings. Therefore it will go though the data, 
	//and then get then return the lowest and the highest score for each rating.
	//We can then test the score of any given car, and check if it falls within
	//the ranges returned from this function, and hence determine their rating.

	public void learner(ArrayList<Car> c)
	{
		//Declaring some variables for use.
		int i = 0;

		allratings = new ArrayList<Double>();

		ArrayList<Double> accratings = new ArrayList<Double>();
		ArrayList<Double> goodratings = new ArrayList<Double>();
		ArrayList<Double> vgoodratings = new ArrayList<Double>();

		//Calculating and storing all ratings for acc cars
		for (i=0; i<c.size(); i++)
		{
			if (c.get(i).getRating().equals("acc"))
				accratings.add(carScorer(c.get(i)));
		}

		//Calculating and storing all ratings for good cars
		for (i=0; i<c.size(); i++)
		{
			if (c.get(i).getRating().equals("good"))
				goodratings.add(carScorer(c.get(i)));
		}

		//Calculating and storing all ratings for vgood cars
		for (i=0; i<c.size(); i++)
		{
			if (c.get(i).getRating().equals("vgood"))
				vgoodratings.add(carScorer(c.get(i)));
		}

		//Now adding only the min and the max rating from each group
		//to the totalratings array, which will be returned from the function.
		//First sorting all arrays
		Collections.sort(accratings);
		Collections.sort(goodratings);
		Collections.sort(vgoodratings);

		//Adding acc lowest and highest ratings.
		allratings.add(accratings.get(0));
		allratings.add(accratings.get(accratings.size()-1));

		//Adding good lowest and highest ratings.
		allratings.add(goodratings.get(0));
		allratings.add(goodratings.get(goodratings.size()-1));


		//Adding vgood lowest and highest ratings.
		allratings.add(vgoodratings.get(0));
		allratings.add(vgoodratings.get(vgoodratings.size()-1));

		//Now the ratings data is saved in the private data variable of
		//this class (allratings).
	}

	//In case I want to get the training data out of the class
	//for something, this function will return the private
	//Arraylist of cars, training data.

	public ArrayList<Car> gettrainingdata()
	{
		return trainingdata;
	}

	public boolean equalcheck(Car a)
	{
		for (int i=0; i<trainingdata.size(); i++)
		{
			if (trainingdata.get(i).equals(a))
				return true;
		}
		return false;
	}
	
	//Use this function only if the previous one returns true!
	//Return an equal car
	public Car returnequal(Car a)
	{
		for (int i=0; i<trainingdata.size(); i++)
		{
			if (trainingdata.get(i).equals(a))
				return trainingdata.get(i);
		}
		return null;
	}
	
	public ArrayList<Car> threesimilarreturn(Car a)
	{
		ArrayList<Car> returnman ;
		double realscore = carScorer(a);
		
		do
		{
			returnman = new ArrayList<Car>();
			
			//If the score of the training data set car is within the specified
			//of the input car, add the training data set car to the return
			//array of cars.
			for (int i= 0; i<trainingdata.size(); i++)
			{
				if (Math.abs(carScorer(trainingdata.get(i))-realscore)<=1)
					returnman.add(trainingdata.get(i));
				if (returnman.size()>=3)
					break;
			}
		}while ((returnman.get(0).equals(returnman.get(1)))||
				(returnman.get(1).equals(returnman.get(2)))||
				(returnman.get(0).equals(returnman.get(2))));
		
		return returnman;
	}

}
