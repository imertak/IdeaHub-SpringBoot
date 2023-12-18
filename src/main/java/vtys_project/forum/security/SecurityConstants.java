package vtys_project.forum.security;

import org.springframework.web.bind.annotation.CrossOrigin;

public class SecurityConstants {
    public static final long JWT_EXPIRATION=60000000; //10 dakika
    public static final long REFRESH_EXPIRATION=3600000; //1 saat
    public static final String JWT_SECRET="RzCjThUaXwzvyyBEHMBeShVmYqtwzzCHEHMcfTjWnZruxADGKaPdSgVkYpsvyyBEHM";
}


