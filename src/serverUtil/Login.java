package serverUtil;

import serverUtil.impl.TriesExceededException;

public interface Login {
    public boolean verify(String username, String password) throws TriesExceededException;
}
