package com.sibb.net.packet.type;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public class Message {
    /**
     * Method getMessage.
     *
     * @return String
     */
    public String getMessage() {
        return message;
    }

    /**
     * Method getType.
     *
     * @return int
     */
    public int getType() {
        return type;
    }

    /**
     * Method getUsername.
     *
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method setMessage.
     *
     * @param message String
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Method setUsername.
     *
     * @param username String
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Constructor for Message.
     *
     * @param username String
     * @param message  String
     * @param type     int
     */
    public Message(String username, String message, int type) {
        this.username = username;
        this.message = message;
        this.type = type;
    }

    private String message;

    private int type;

    private String username;
}
