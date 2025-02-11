package main.spring_application.exception;

public class ResourcesNotFoundException extends RuntimeException{

    public ResourcesNotFoundException(String message) {
        super(message);
    }
}
