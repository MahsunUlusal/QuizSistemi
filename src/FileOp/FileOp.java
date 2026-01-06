package FileOp;

public abstract class FileOp {
	/**
	 * Dosyadan ekleme işlemini alt sınıfların override etmesi için tanımlar
	 */
	void add() {
	}
	
	/**
	 * Dosyadan değiştirme işlemini alt sınıfların override etmesi için tanımlar
	 */
	void change() {
	}
	
	/**
	 * Dosyadan çıkarma işlemini alt sınıfların override etmesi için tanımlar
	 */
	void remove() {
	}
	
	/**
	 * Dosyadan gösterme işlemini alt sınıfların override etmesi için tanımlar
	 * @return İstenilen veriyi döndürür.
	 */
	String show() {
		return null;
	}
}
