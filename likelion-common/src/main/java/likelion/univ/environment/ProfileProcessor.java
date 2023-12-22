package likelion.univ.environment;

import likelion.univ.annotation.Processor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;

import java.lang.reflect.Array;
import java.util.Arrays;

@Processor
@RequiredArgsConstructor
public class ProfileProcessor {
    private final Environment environment;

    public Boolean isDev(){
        String[] activeProfiles = environment.getActiveProfiles();
        return Arrays.stream(activeProfiles).anyMatch(env -> (env.equalsIgnoreCase("dev") ));
    }
    public Boolean isStag(){
        String[] activeProfiles = environment.getActiveProfiles();
        return Arrays.stream(activeProfiles).anyMatch(env -> (env.equalsIgnoreCase("stag") ));
    }
    public Boolean isProd(){
        String[] activeProfiles = environment.getActiveProfiles();
        return Arrays.stream(activeProfiles).anyMatch(env -> (env.equalsIgnoreCase("prod") ));
    }
}
