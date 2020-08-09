package pacecorradetti;



public class HashIndirizzamentoAperto {

	protected Object[] m;
	private static Object deleted;
	private int length = 0;
	
	public int getStringHash(String s,int l) {
		int asciiValue = 0;
		
		for(int i = 0;i < s.length();i++) {
			asciiValue = asciiValue + s.charAt(i);
		}
		return Math.abs(asciiValue % l);
		
	}
	public int getStringHash2(String s,int l) {
		int asciiValue = 0;
		
		for(int i = 0;i < s.length();i++) {
			asciiValue = (int) (asciiValue + s.charAt(i) + 1);
		}
		return Math.abs(asciiValue % l);
		
	}
	
	public HashIndirizzamentoAperto(int l) {
		this.length = l;
		m = new Object[length];
		for(int i =0;i < m.length;i++) {
			m[i] = null;
		}
		this.deleted = new Object();
	}
	public void printHash()
	{
		for(int i =0; i< m.length;i++)
		{
			if(m[i] == null) System.out.print(i + " ");
			else if(m[i].equals(deleted)) System.out.print("deleted");
			else System.out.print(m[i].getClass().getName() + " ");
		}
	}
	
	public Object search(Object o,String name,int l) throws MovidaKeyException {
		int key = getStringHash(name, l);
		int key2 = getStringHash2(name,l);
		if(m[key].equals(o)) return m[key];
		else if(m[key] == null) return null;
		else {
			for(int i=0;i < m.length;i++) {
				if((m[key + (i * key2) % l].equals(o))) 
					return m[(key + (i * key2) % l)];
			}
		}
		throw new MovidaKeyException();
	}
	
	public void delete(Object o,String name,int l) throws MovidaKeyException
	{
		int key = getStringHash(name, l);
		int key2 = getStringHash(name, l);
		if(m[key].equals(o))
		{ 
			m[key] = deleted;
		}
		else if(m[key] == null) throw new MovidaKeyException();
		else if(m[key] == deleted)
		{
			for(int i=0;i < m.length;i++) {
				if((m[key + (i * key2) % l].equals(o)))
				{
					m[key + (i * key2) % l] = deleted;
				}
		}
		}
	}
	// returns -1 if the array is full
	public int insert(Object o,String s,int l) {
		int key = getStringHash(s,l);
		int key2 = getStringHash2(s,l);
		if((m[key] == null) || (m[key] == deleted)) {
			m[key] = o ;
		return (key);
		}
		else if(m[key] == o) {
			m[key] = o;
			return(key);
		}
		else  {
			for(int i=0;i < m.length;i++) {
				if((m[key + (i * key2) % l] == null) || ((m[key + (i * key2) % l] == deleted)))
				{
					m[key + (i * key2) % l] = o;
					return(key + (i * key2) % l);
				}
			}
		}
			/*boolean found = false;
			for(int i =key +1;i < m.length;i++) {
				if(m[i] == null) 
				{
					m[i] = o;
				found = true;
				return(i);
				}
			}
			if(!found) {
				for(int i =0;i<key;i++)
				{
					if(m[i]== null) m[i] = o;
					return(i);
				}
			}
		}*/
		return -1;
		
	}

}
