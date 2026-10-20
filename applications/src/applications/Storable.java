package applications;

import java.io.IOException;

public interface Storable<E> {
	void save(E data);

	E load();
}
