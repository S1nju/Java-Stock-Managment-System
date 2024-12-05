package Dashboard;

public class ProductData {
	private Integer Pid;
	private String Name;
	private String Category;
	private Integer Qte;
	private String Type;
	private String Source;
	private Integer Price;
	public ProductData(Integer Pid,String Name,String Category,Integer Qte,String Type,Integer Price,String Source) {
		
		this.Pid=Pid;
		this.Name=Name;
		this.Category=Category;
		this.Qte=Qte;
		this.Price=Price;
		this.Type=Type;
		this.Source=Source;
	}
	public Integer  getPid() {
		return this.Pid;
	}

	public String  getName() {
		return this.Name;
	}
	public String  getCategory() {
		return this.Category;
	}
	public Integer  getQte() {
		return this.Qte;
	}
	public String  getType() {
		return this.Type;
	}
	public String  getSource() {
		return this.Source;
	}
	public Integer  getPrice() {
		return this.Price;
	}
}
