package subway.file;

import java.util.List;

public interface DomainLoader {
    public <T> List<T> getDomains();
    public <T> T createDomain(List<String> args);
}
