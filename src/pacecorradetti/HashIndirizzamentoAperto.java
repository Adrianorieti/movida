package pacecorradetti;


public class HashIndirizzamentoAperto<K extends Comparable<K>,V extends Object> {

	protected class Pair {
		K key;
		V elem;
		
		Pair(K key, V elem) {
			this.key = key;
			this.elem = elem;
		}
	}  
	protected Pair[] m;
	private static Object deleted;
	private int length = 0;
	
	
	
//	public int getStringHash(K s,int l) {
//		int asciiValue = 0;
//		
//		for(int i = 0;i < s.length();i++) {
//			//asciiValue = asciiValue + s.charAt(i);
//			
//		}
//		return Math.abs(asciiValue % l);
//		
//	}
//	public int getStringHash2(String s,int l) {
//		int asciiValue = 0;
//		
//		for(int i = 0;i < s.length();i++) {
//			asciiValue = (int) (asciiValue + s.charAt(i) + 1);
//		}
//		return Math.abs(asciiValue % l);
//		
//	}
//	
	public HashIndirizzamentoAperto(int l)
	{
		this.length = l;
		m =   (Pair[]) new Object[length];
		for(int i =0;i < m.length;i++) 
		{
			m[i] = null;
		}
		this.deleted = new Object();
	}
	public void printHash()
	{
		for(int i =0; i< m.length;i++)
		{
			if(m[i] == null) System.out.print(i + " ");
			else if(m[i].elem.equals(deleted)) System.out.print("deleted");
			else System.out.print(m[i].key);
		}
	}
	
	public V search(K key) throws MovidaKeyException
	{
		int hash = key.hashCode() % length;
		if(m[hash].key.equals(key)) return (m[hash].elem);
		else if(m[hash] == null) return null;
		else {
			for(int i=0;i < m.length;i++) 
			{
				int hash2 = hash + (i * (key.hashCode() + 1)) % length ;
				if(m[hash2].key.equals(key)) 
					return m[hash2].elem;
			}
		}
		throw new MovidaKeyException();
	}
	
	public void delete(K key) throws MovidaKeyException
	{
		int hash = key.hashCode() % length;
		if(m[hash].key.equals(key))
		{ 
			m[hash].elem = (V) deleted;
		}
		else if(m[hash] == null) throw new MovidaKeyException();
		else if(m[hash].elem == deleted)
		{
			for(int i=0;i < m.length;i++) 
			{
				int hash2 = hash + (i * (key.hashCode() + 1)) % length ;
				if((m[hash2].key.equals(key)))
				{
					m[hash2].elem = (V) deleted;
				}
		}
		}
	}
	
	public void insert(K key,V value) throws MovidaBoundException
	{
		int hash = key.hashCode() % length;
		boolean inserted = false;
		if((m[hash] == null) || (m[hash] == deleted))
		{
			m[hash] = new Pair(key,value) ;
			inserted = true;
		}
		else if(m[hash].key == key)
		{
			m[hash].elem = value; // sovrascrive l'oggetto
			inserted = true;
		}
		else 
		{
			for(int i=0;i < m.length;i++)
			{
				int hash2 = hash + (i * (key.hashCode() + 1)) % length ;
				if((m[hash2] == null) || ((m[hash2] == deleted)))
				{
					m[hash2] = new Pair(key,value);
					inserted = true;
					break;
				}
			}
			
		}
		if(!inserted) throw new MovidaBoundException();
	}

}
