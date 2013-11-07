package com.nodovitt.objects;

public class Result implements Comparable<Result> {
	public double distance;
	public String name;
	public String address;
	public double lat;
	public double lng;
	public String phone;
	public float rating;

	/*public boolean equals(Result r) {

		if (this.phone.equals(r.phone)) {
			System.out.println(this.phone);
			return false;
		}
		return true;

	}

	public int hashCode() {
		int hash = 7;		
		hash = 31 * hash + (null == phone ? 0 : phone.hashCode());
		return hash;
	}
	*/

	@Override
	public int compareTo(Result a) {
		if (a.distance > this.distance)
			return -1;
		else if (a.distance < this.distance)
			return 1;
		else
			return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Result other = (Result) obj;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

}