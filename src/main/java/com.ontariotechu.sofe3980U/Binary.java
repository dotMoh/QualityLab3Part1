package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'
	/**
	* A constructor that generates a binary object.
	*
	* @param number a String of the binary values. It should contain only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	*/
	public Binary(String number) {
		if (number == null || number.isEmpty()) {
			this.number = "0"; // Default to "0" for null or empty input
			return;
		}
	
		// Validate the binary string (only '0' or '1' allowed)
		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch != '0' && ch != '1') {
				this.number = "0"; // Default to "0" for invalid input
				return;
			}
		}
	
		// Remove leading zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg) != '0') {
				break;
			}
		}
	
		// If all digits are '0', ensure number is "0"
		this.number = (beg == number.length()) ? "0" : number.substring(beg);
	
		// Ensure empty strings are replaced with "0"
		if (this.number.isEmpty()) {
			this.number = "0";
		}
	}
	/**
	* Return the binary value of the variable
	*
	* @return the binary value in a string format.
	*/
	public String getValue()
	{
		return this.number;
	}
	/**
	* Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param num1 The first addend object
	* @param num2 The second addend object
	* @return A binary variable with a value of <i>num1+num2</i>.
	*/
	public static Binary add(Binary num1,Binary num2)
	{
		// the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		//initial variable
		int carry=0;
		String num3="";  // the binary value of the sum
		while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
		{
			int sum=carry; // previous carry
			if(ind1>=0){ // if num1 has a digit to add
				sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if(ind2>=0){ // if num2 has a digit to add
				sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
				ind2--; //update ind2
			}
			carry=sum/2; // the new carry
			sum=sum%2;  // the resultant digit
			num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
		}
		Binary result=new Binary(num3);  // create a binary object with the calculated value.
		return result;
		
	}

	public static Binary performOR(Binary first, Binary second) {
		StringBuilder output = new StringBuilder();
		int index1 = first.number.length() - 1;
		int index2 = second.number.length() - 1;
	
		while (index1 >= 0 || index2 >= 0) {
			char bitA = index1 >= 0 ? first.number.charAt(index1) : '0';
			char bitB = index2 >= 0 ? second.number.charAt(index2) : '0';
			output.append((bitA == '1' || bitB == '1') ? '1' : '0');
			index1--;
			index2--;
		}
	
		return new Binary(output.reverse().toString());
	}
	
	public static Binary performAND(Binary first, Binary second) {
		StringBuilder output = new StringBuilder();
		int index1 = first.number.length() - 1;
		int index2 = second.number.length() - 1;
	
		while (index1 >= 0 || index2 >= 0) {
			char bitA = index1 >= 0 ? first.number.charAt(index1) : '0';
			char bitB = index2 >= 0 ? second.number.charAt(index2) : '0';
			output.append((bitA == '1' && bitB == '1') ? '1' : '0');
			index1--;
			index2--;
		}
	
		return new Binary(output.reverse().toString());
	}
	
	public static Binary multiplyBinary(Binary first, Binary second) {
		Binary result = new Binary("0");
		Binary temp = new Binary(first.number);
	
		for (int i = second.number.length() - 1; i >= 0; i--) {
			if (second.number.charAt(i) == '1') {
				result = addBinaries(result, temp);
			}
			temp = shiftLeftByOne(temp);
		}
	
		return result;
	}
	
	private static Binary shiftLeftByOne(Binary input) {
		if ("0".equals(input.getValue())) {
			return new Binary("0");
		}
		return new Binary(input.number + "0");
	}
	
	private static Binary addBinaries(Binary bin1, Binary bin2) {
		StringBuilder sum = new StringBuilder();
		int carry = 0, i = bin1.number.length() - 1, j = bin2.number.length() - 1;
	
		while (i >= 0 || j >= 0 || carry > 0) {
			int bit1 = i >= 0 ? bin1.number.charAt(i) - '0' : 0;
			int bit2 = j >= 0 ? bin2.number.charAt(j) - '0' : 0;
			int total = bit1 + bit2 + carry;
			sum.append(total % 2);
			carry = total / 2;
			i--;
			j--;
		}
	
		return new Binary(sum.reverse().toString());
	}
	

}


