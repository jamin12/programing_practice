package org.jamin.reactive02.section01.class05;

import org.jamin.reactive02.common.SampleData;

import lombok.Setter;

@Setter
public class CryptoCurrencyPriceEmitter {
	private CryptoCurrencyPriceListener listener;

	public void flowInto() {
		listener.onPrice(SampleData.btcPrices);
	}

	public void complete() {
		listener.onComplete();
	}
}