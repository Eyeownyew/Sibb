package com.sibb;

import com.sibb.net.Constructor;
import com.sibb.world.entity.Player;
import org.jboss.netty.channel.Channel;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public class Client {

    static Client client = null;
    public static char validChars[] = {' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
            'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B',
            'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
            'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            ' ', '!', '?', '.', ',', ':', ';', '(', ')', '-', '&', '*', '\\', '\'', '@', '#', '+',
            '=', '\243', '$', '%', '"', '[', ']', '>', '<', '^', '/', '_', '{', '}', '~', '`', '|'};

    /**
     * Method getClient.
     *
     * @return Client
     */
    public synchronized static Client getClient() {
        return client;
    }

    /**
     * Method getChannel.
     *
     * @return Channel
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * Method getConstructor.
     *
     * @return Constructor
     */
    public Constructor getConstructor() {
        return constructor;
    }

    /**
     * Method getPassword.
     *
     * @return String
     */
    public String getPassword() {
        return password;
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
     * Method setChannel.
     *
     * @param channel Channel
     */
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    /**
     * Method setConstructor.
     *
     * @param constructor Constructor
     */
    public void setConstructor(Constructor constructor) {
        this.constructor = constructor;
    }

    /**
     * Method setPassword.
     *
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
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
     * Constructor for Client.
     *
     * @param channel Channel
     */
    public Client(Channel channel) {
        this.channel = channel;
        client = this;
        constructor = new Constructor(this);
    }

    Channel channel = null;

    protected Constructor constructor = null;

    String password = "";

    String username = "";

    private Player player = new Player(0, 0);

    /**
     * Method getPlayer.
     *
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Method setPlayer.
     *
     * @param player Player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Method movePlayer.
     *
     * @param dX int
     * @param dY int
     */
    public void movePlayer(int dX, int dY) {
        player.translate(dX, dY);
    }
}
