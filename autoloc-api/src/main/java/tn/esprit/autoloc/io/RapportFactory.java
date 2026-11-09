package tn.esprit.autoloc.io;

public class RapportFactory {

    public static IRapport creer(Class<? extends IRapport> type) {
        try {
            IRapport rapport = type.getDeclaredConstructor().newInstance();
            return type.isAnnotationPresent(Console.class) ? new ConsoleRapportDecorator(rapport) : rapport;
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(e);
        }
    }
}
