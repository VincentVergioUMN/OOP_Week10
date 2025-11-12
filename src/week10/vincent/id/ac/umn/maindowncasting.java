package week10.vincent.id.ac.umn;

public class maindowncasting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CEO c = new CEO();
		Pekerja p = new CEO();
		c = (CEO)p;
		c.tanyaPendapatan();
	}

}
