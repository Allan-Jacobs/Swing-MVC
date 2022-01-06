package util;

import org.reflections.Configuration;
import org.reflections.Reflections;
import org.reflections.Store;
import org.reflections.scanners.Scanner;

public class ReflectionsServiceImpl extends Reflections implements ReflectionsService {
    public ReflectionsServiceImpl(Configuration configuration) {
        super(configuration);
    }

    public ReflectionsServiceImpl(Store store) {
        super(store);
    }

    public ReflectionsServiceImpl(String prefix, Scanner... scanners) {
        super(prefix, scanners);
    }

    public ReflectionsServiceImpl(Object... params) {
        super(params);
    }

    protected ReflectionsServiceImpl() {
        super();
    }
}
