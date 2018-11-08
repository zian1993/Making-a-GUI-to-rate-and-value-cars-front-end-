//Hassan M. Khan
//I.D: 001168658
//JAVA

package project1;

public class Car implements CarADT {

	//Declaring the private data types of the class
	private String buycost;
	private String maintenance; 
	private int numdoors; 
	private int numpersons;
	private String bootsize;
	private String safetyrate;
	private String classify;

	//Starting off with the default constructor
	public Car()
	{
		//This is the parameter less constructor, so setting
		//everything to basically nothing. 
		buycost = new String();
		maintenance = new String();
		numdoors = 0;
		numpersons = 0;
		bootsize = new String();
		safetyrate = new String();
		classify = new String();
	}

	//Creating the parameterized constructor
	public Car(String buy, String maint, int door, int person, String boot, String safe, String hmm)
	{
		//So this is the real constructor, setting all
		//private datafields according to the input variables.
		buycost = new String(buy);
		maintenance = new String(maint);
		numdoors = door;
		numpersons = person;
		bootsize = new String(boot);
		safetyrate = new String(safe);
		classify = new String(hmm);
	}

	//Now finally we can start implementing the methods of the interface.
	@Override
	public void setBuying (String s)
	{
		buycost = s;
	}
	@Override
	public void setMaint (String m)
	{
		maintenance = m;
	}
	@Override
	public void setDoors (int numDoors) // set “more” as 5
	{
		numdoors = numDoors;
	}
	@Override
	public void setPersons (int numPersons ) // set “more” as 5
	{
		numpersons = numPersons;
	}
	@Override
	public void setTrunk(String t)
	{
		bootsize = t;
	}
	@Override
	public void setSafety (String s)
	{
		safetyrate = s;
	}
	@Override
	public void setRating (String c)
	{
		classify = c;
	}
	@Override
	public String getRating()
	{
		return classify;
	}

	//Creating some getters for the cars data, so that it can analysed
	//for prediction calculations
	public String getBuy()
	{
		return buycost;
	}

	public String getMaint()
	{
		return maintenance;
	}

	public int getDoors()
	{
		return numdoors;
	}

	public int getPersons()
	{
		return numpersons;
	}

	public String getBoot()
	{
		return bootsize;
	}

	public String getSafety()
	{
		return safetyrate;
	}


	//Creating a toString method to print the car if need be
	@Override
	public String toString()
	{
		return ("["+"buy:"+buycost+", "+"maint:"+maintenance+", "+"doors:"+numdoors+", "+ "persons:"+numpersons+", "+
				"boot:"+bootsize+", "+"safety:"+safetyrate+", "+"rating:"+classify+"]");
	}
	
	//Adding an equals method to check cars against one another.
	public boolean equals(Car a)
	{
		if ((buycost.equals(a.getBuy()))&&(maintenance.equals(a.getMaint()))
				&&(numdoors == a.getDoors())&&(numpersons == a.getPersons())
				&&(bootsize.equals(a.getBoot()))&&(safetyrate.equals(a.getSafety())))
		{
			return true;
		}
		return false;
	}

}
