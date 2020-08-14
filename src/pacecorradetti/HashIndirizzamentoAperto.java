package pacecorradetti;

import java.security.KeyStore.Entry;
import java.util.HashSet;
import java.util.Set;

public class HashIndirizzamentoAperto<K extends Comparable<K>,V extends Object> extends Map<K,V> {

	 
	protected Entry[] m;
	private static Object deleted;
	private int length = 0;
	
	public HashIndirizzamentoAperto(int l)
	{
		this.length = l;
		m =   (Entry[]) new Object[length];
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
			else if(m[i].value.equals(deleted)) System.out.print("deleted");
			else System.out.print(m[i].key);
		}
	}
	
	@Override
	public V search(K key) throws MovidaKeyException
	{
		int hash = key.hashCode() % length;
		if(m[hash].key.equals(key)) return (V) (m[hash].value);
		else if(m[hash] == null) return null;
		else {
			for(int i=0;i < m.length;i++) 
			{
				int hash2 = hash + (i * (key.hashCode() + 1)) % length ;
				if(m[hash2].key.equals(key)) 
					return (V) m[hash2].value;
			}
		}
		throw new MovidaKeyException();
	}
	
	@Override
	public void delete(K key) throws MovidaKeyException
	{
		int hash = key.hashCode() % length;
		if(m[hash].key.equals(key))
		{ 
			m[hash].value = (V) deleted;
		}
		else if(m[hash] == null) throw new MovidaKeyException();
		else if(m[hash].value == deleted)
		{
			for(int i=0;i < m.length;i++) 
			{
				int hash2 = hash + (i * (key.hashCode() + 1)) % length ;
				if((m[hash2].key.equals(key)))
				{
					m[hash2].value = (V) deleted;
				}
		}
		}
	}
	
	@Override
	public void put(K key,V value) 
	{
		int hash = key.hashCode() % length;
		boolean inserted = false;
		if((m[hash] == null) || (m[hash] == deleted))
		{
			m[hash] = new Entry(key,value) ;
			inserted = true;
		}
		else if(m[hash].key == key)
		{
			m[hash].value = value; // sovrascrive l'oggetto
			inserted = true;
		}
		else 
		{
			for(int i=0;i < m.length;i++)
			{
				int hash2 = hash + (i * (key.hashCode() + 1)) % length ;
				if((m[hash2] == null) || ((m[hash2] == deleted)))
				{
					m[hash2] = new Entry(key,value);
					inserted = true;
					break;
				}
			}
			
		}
	}
	
	@Override
	public void clear()
	{
		for(int i = 0;i < m.length;i++)
		{
			m[i] = null;
		}
	}

	@Override
	public Set<Map<K, V>.Entry> entrySet() {
		Set<Map<K, V>.Entry> temp = new HashSet<Map<K,V>.Entry>();
		for (Map<K, V>.Entry e : m)
		{
			temp.add(e);
		}
		return temp;
	}
}