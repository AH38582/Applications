package applications;

public interface Storable<E> { 
 void save(E data);
 E load();
}
