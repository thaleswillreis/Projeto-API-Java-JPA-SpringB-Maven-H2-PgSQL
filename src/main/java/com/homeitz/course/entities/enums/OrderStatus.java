package com.homeitz.course.entities.enums;

public enum OrderStatus {
	
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
		//variável para guardar o tipo enumerado
	private int code;
	
	private OrderStatus(int code) {
		this.code = code;
	}
	
		//método público para retornar o code
	public int getCode() {
		return code;
	}
	
		//método estático para receber um inteiro, e retornar esse valor inteiro convertido para um tipo enumerado
	public static OrderStatus valueOf(int code) {
			//percorre os valores do enum, compara com o conteúdo recebido em "code" e retorna se for igual.
		for (OrderStatus value : OrderStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
}
