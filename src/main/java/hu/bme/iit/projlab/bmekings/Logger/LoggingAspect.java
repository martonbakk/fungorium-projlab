package hu.bme.iit.projlab.bmekings.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import hu.bme.iit.projlab.bmekings.Player.Player;

@Aspect
public class LoggingAspect {
    @Before("execution(* *(..)) && @annotation(hu.bme.iit.projlab.bmekings.Logger.Loggable)")
    public void logMethodCall(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        String methodName = joinPoint.getSignature().getName();

        // Osztály annotációjának kiolvasása
        Loggable classLabel = target.getClass().getAnnotation(Loggable.class);
        String className = (classLabel != null && !classLabel.value().isEmpty()) ? classLabel.value() : target.getClass().getSimpleName();

        // PlayerId kiolvasása
        String playerId = "unknown";
        if (target instanceof Player player) {
            playerId = player.getPlayerID();
        }

        // Metódus annotációjának kiolvasása
        Loggable loggable = null;
        try {
            loggable = target.getClass()
                .getMethod(methodName, joinPoint.getSignature().getDeclaringType().getMethods()[0].getParameterTypes())
                .getAnnotation(Loggable.class);
        } catch (NoSuchMethodException e) {
            // Ha a metódus nem található, csendben kilépünk
            return;
        }

        // Metódus nevének meghatározása
        String finalMethodName = methodName;
        if (loggable != null && !loggable.value().isEmpty()) {
            finalMethodName = loggable.value();
        }

        // Naplózás
        MethodLogger.logMethodCall(className, playerId, finalMethodName);
    }
}