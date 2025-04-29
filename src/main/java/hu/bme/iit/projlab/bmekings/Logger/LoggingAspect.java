package hu.bme.iit.projlab.bmekings.Logger;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import hu.bme.iit.projlab.bmekings.Entities.Entity;
import hu.bme.iit.projlab.bmekings.Map.Map;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
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
        String targetID = "unknown";
        if (target instanceof Player player) {
            targetID = player.getPlayerID();
        }
        if (target instanceof Entity entity){
            targetID = entity.getId();
        }

        if (target instanceof Tecton tecton){
            targetID = tecton.getId();
        }
        if (target instanceof Map){
            targetID = "MP1";
        }

        // Most: helyesen szerezd meg a Method objektumot
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // A metódus annotációját kérdezzük
        Loggable loggable = method.getAnnotation(Loggable.class);

        // Metódus nevének meghatározása
        String finalMethodName = methodName;
        if (loggable != null && !loggable.value().isEmpty()) {
            finalMethodName = loggable.value();
        }

        // Naplózás
        MethodLogger.logMethodCall(className, targetID, finalMethodName);
    }
}