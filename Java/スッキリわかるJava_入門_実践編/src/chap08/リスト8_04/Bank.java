package chap08.リスト8_04;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Bank {

	private String accountNumber;
	private int balance;

	public Bank(String accountNumber, int balance) {
		this.accountNumber	= accountNumber;
		this.balance		= balance;
	}

	@Override
	public String toString() {
		return String.format("%s: %d", accountNumber, balance);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
