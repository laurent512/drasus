package controller;

import java.io.IOException;

import model.*;

import com.esotericsoftware.kryo.*;
import com.esotericsoftware.kryonet.*;
import controller.ConnexionController;

public class ConnexionController {

    /**
     * @param args
     */

    private Client client;
    private Msg msg = new Msg();
    private Player[] envoi;
    private Player[] recu;

    public ConnexionController(String adr) {

	client = new Client();

	Kryo kryo = client.getKryo();
	kryo.register(model.Player.class);
	kryo.register(model.Msg.class);
	kryo.register(model.UnitFactoryPegasus.class);
	kryo.register(java.util.Hashtable.class);
	kryo.register(model.Archer.class);
	kryo.register(model.ArcherMonte.class);
	kryo.register(model.AttackCaC.class);
	kryo.register(model.AttackCaCBerserker.class);
	kryo.register(model.AttackDistance.class);
	kryo.register(model.Berserker.class);
	kryo.register(model.Bretteur.class);
	kryo.register(model.Cavalier.class);
	kryo.register(model.Chevalier.class);
	kryo.register(model.DeadUnitException.class);
	kryo.register(model.Eclaireur.class);
	kryo.register(model.Fantassin.class);
	kryo.register(model.IAttack.class);
	kryo.register(model.Lancier.class);
	kryo.register(model.Rodeur.class);
	kryo.register(model.Tank.class);
	kryo.register(model.Unit.class);
	kryo.register(model.UnitFactory.class);
	kryo.register(model.UnitFactoryPegasus.class);
	kryo.register(model.Weapon.class);
	kryo.register(view.Tile.class);
	kryo.register(model.Player[].class);
	kryo.register(view.Tile.FIELD.class);
	kryo.register(String[].class);

	client.start();

	client.addListener(new Listener() {
	    public void received(Connection connection, Object object) {
		if (object instanceof Player[]) {
		    recu = (Player[]) object;
		}
		if (object instanceof Msg) {
		    msg = (Msg) object;
		}
	    }
	});

	try {
	    client.connect(5000, adr, 4662, 4672);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void sendPlayer(Player player) {
	envoi = new Player[1];
	envoi[0] = player;
	client.sendTCP(envoi);
    }

    public void sendPlayers(Player a, Player b) {
	envoi = new Player[2];
	envoi[0] = a;
	envoi[1] = b;
	client.sendTCP(envoi);
    }

    public void sendMsg(Msg msg) {
	client.sendTCP(msg);
    }

    public Player[] getPlayer() {
	return recu;
    }

    public void setNull() {
	recu = null;
    }

    public void eraseMsg() {
	msg = null;
    }

    public Msg getMsg() {
	return msg;
    }
}
