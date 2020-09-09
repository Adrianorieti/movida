package pacecorradetti;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HashIndirizzamentoAperto<K extends Comparable<K>,V> extends pacecorradetti.Map<K,V> {

	 
	private Entry[] m;
	private static Object deleted;
	private int length = 0;
	private int size = 0;
	
	@SuppressWarnings("unchecked")
	public HashIndirizzamentoAperto(int l)
	{
		this.length = l;
		m =  (Entry[]) Array.newInstance(Entry.class , length);
		for(int i =0;i < m.length;i++) 
		{
			m[i] = null;
		}
		HashIndirizzamentoAperto.deleted = new Object();
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
		int hash = Math.abs(key.hashCode() % length);
		if(m[hash].key.equals(key)) return (V) (m[hash].value);
		else if(m[hash] == null) 
		{
			throw new MovidaKeyException();
		}
		else 
		{
			for(int i=0;i < m.length;i++) 
			{
				int hash2 =  Math.abs((hash + (i * ( key.hashCode() + 1))) % length) ;
				if(m[hash2].key.equals(key)) 
					return (V) m[hash2].value;
			}
		}
		throw new MovidaKeyException();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void delete(K key) throws MovidaKeyException
	{
		int hash = Math.abs(key.hashCode() % length);
		if(m[hash].key.equals(key))
		{ 
			m[hash].value = (V) deleted;
			size--;
		}
		else if(m[hash] == null) 
		{
			throw new MovidaKeyException();
		}
		else if(m[hash].value == deleted)
		{
			for(int i=0;i < m.length;i++) 
			{
				int hash2 =  Math.abs((hash + (i * ( key.hashCode() + 1))) % length) ;
				if((m[hash2].key.equals(key)))
				{
					m[hash2].value = (V) deleted;
					size--;
				}
			}
		}
	}
	
	@Override
	public void put(K key,V value) 
	{
		int hash =  Math.abs(key.hashCode() % length);
		if((m[hash] == null) || (m[hash] == deleted))
		{
			m[hash] = new Entry(key,value) ;
			size++;
			
		}
		else if(m[hash].key.equals(key))
		{
			m[hash].value = value; // sovrascrive l'oggetto
		}
		else 
		{
			for(int i=0;i < m.length;i++)
			{
				int hash2 = Math.abs((hash + (i * ( key.hashCode() + 1))) % length) ;
				if((m[hash2] == null) || ((m[hash2] == deleted)))
				{
					m[hash2] = new Entry(key,value);
					size++;
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
			if(e != null && !e.getValue().equals(deleted))
			{
			temp.add(e);
			}
		}
		return temp;
	}
	
	
	public ArrayList<V> valueList() {
		ArrayList<V> temp = new ArrayList<V>();
		for (int i = 0; i < m.length; i++)
		{
			if(m[i] != null && m[i].value != deleted)
			temp.add(m[i].getValue());
		}
		return temp;
	}
	
	
	@Override
	public void putIfAbsent(K key, V value) {
		int hash =  Math.abs(key.hashCode() % length);
		if((m[hash] == null) || (m[hash] == deleted))
		{
			m[hash] = new Entry(key,value) ;
			size++;
			
		}
		else if(m[hash].key.equals(key))
		{
			return; 
		}
		else 
		{
			for(int i=0;i < m.length;i++)
			{
				int hash2 = Math.abs((hash + (i * ( key.hashCode() + 1))) % length) ;
				if((m[hash2] == null) || ((m[hash2] == deleted)))
				{
					m[hash2] = new Entry(key,value);
					size++;
					break;
				}
			}
			
		}
		
	}
	@Override
	public int size() {
		return size;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HashIndirizzamentoAperto [HashTable=");
		builder.append(Arrays.toString(m));
		builder.append("]");
		return builder.toString();
	}
}