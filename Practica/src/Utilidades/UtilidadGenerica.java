package Utilidades;

public class UtilidadGenerica {

    public static <T> void mostrarElementos(T... elementos) {
        for (T elemento : elementos) {
            System.out.println(elemento);
        }
    }

    public static <T> boolean existeElemento(
            T buscado, T... elementos) {

        for (T elemento : elementos) {
            if (buscado == null) {
                if (elemento == null) {
                    return true;
                }
            } else if (buscado.equals(elemento)) {
                return true;
            }
        }

        return false;
    }
}
