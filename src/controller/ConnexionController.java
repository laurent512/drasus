package controller;

import java.io.IOException;

import model.Msg;
import model.Player;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 * @author Adrien
 */

public class ConnexionController {

	public static String IP = "spyl.net";
	public static String PORT = "6667";
	private Client client;
	private Msg msg = new Msg();
	private Player[] envoi;
	private Player[] recu;

	public ConnexionController() {

		client = new Client();

		Kryo kryo = client.getKryo();
		kryo.register(model.Player.class);
		kryo.register(model.Msg.class);
		kryo.register(view.Tile.class);
		kryo.register(model.Player[].class);
		kryo.register(view.Tile.FIELD.class);
		kryo.register(String[].class);
		kryo.register(model.UnitFactory.class);
		kryo.register(model.UnitFactoryDragon.class);
		kryo.register(model.UnitFactoryPegasus.class);
		kryo.register(model.attack.Attack.class);
		kryo.register(model.attack.AttackCaC.class);
		kryo.register(model.attack.AttackCaCBerserker.class);
		kryo.register(model.attack.AttackCaCBerserkerBoosted.class);
		kryo.register(model.attack.AttackCaCBoostedInHill.class);
		kryo.register(model.attack.AttackCaCChevalier.class);
		kryo.register(model.attack.AttackCaCCripple.class);
		kryo.register(model.attack.AttackCaCCrippleLonger.class);
		kryo.register(model.attack.AttackCaCIgnoreArmor.class);
		kryo.register(model.attack.AttackDistance.class);
		kryo.register(model.attack.AttackDistanceArcher.class);
		kryo.register(model.attack.AttackDistanceDoubledAgainstCavalry.class);
		kryo.register(model.attack.AttackDistanceDoubledInForest.class);
		kryo.register(model.attack.AttackDistancePoisoned.class);
		kryo.register(model.attack.AttackDistancePoisonedLonger.class);
		kryo.register(model.exception.DeadBossException.class);
		kryo.register(model.exception.DeadUnitException.class);
		kryo.register(model.exception.VictoryException.class);
		kryo.register(model.units.ArcherDragon.class);
		kryo.register(model.units.ArcherMonteDragon.class);
		kryo.register(model.units.ArcherMontePegasus.class);
		kryo.register(model.units.ArcherPegasus.class);
		kryo.register(model.units.BerserkerDragon.class);
		kryo.register(model.units.BerserkerPegasus.class);
		kryo.register(model.units.BretteurDragon.class);
		kryo.register(model.units.BretteurPegasus.class);
		kryo.register(model.units.CavalierDragon.class);
		kryo.register(model.units.CavalierPegasus.class);
		kryo.register(model.units.ChevalierDragon.class);
		kryo.register(model.units.ChevalierPegasus.class);
		kryo.register(model.units.Dragon.class);
		kryo.register(model.units.EclaireurDragon.class);
		kryo.register(model.units.EclaireurPegasus.class);
		kryo.register(model.units.FantassinDragon.class);
		kryo.register(model.units.FantassinPegasus.class);
		kryo.register(model.units.LancierDragon.class);
		kryo.register(model.units.LancierPegasus.class);
		kryo.register(model.units.Pegasus.class);
		kryo.register(model.units.RodeurDragon.class);
		kryo.register(model.units.RodeurPegasus.class);
		kryo.register(model.units.TankDragon.class);
		kryo.register(model.units.TankPegasus.class);
		kryo.register(model.units.Unit.class);
		kryo.register(java.util.Hashtable.class);
		kryo.register(model.units.Unit.Weapon.class);

		client.start();

		client.addListener(new Listener() {
			@Override
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
			client.connect(5000, IP, Integer.parseInt(PORT));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Put the last message received to null.
	 */
	public void eraseMsg() {
		msg = null;
	}

	/**
	 * Returns the last message received.
	 * 
	 * @return the last message received
	 */
	public Msg getMsg() {
		return msg;
	}

	/**
	 * Returns the last array of player received.
	 * 
	 * @return An array of player
	 */
	public Player[] getPlayer() {
		return recu;
	}

	/**
	 * Send a message.
	 * 
	 * @param msg
	 *            message to send
	 */
	public void sendMsg(Msg msg) {
		System.out.println(msg.getMsg());
		client.sendTCP(msg);
	}

	/**
	 * Return the state of the connection
	 * 
	 * @return true if the client is connected to the server
	 */
	public boolean isConnected() {
		return client.isConnected();
	}

	/**
	 * Send a player.
	 * 
	 * @param player
	 *            The player to send
	 */
	public void sendPlayer(Player player) {
		envoi = new Player[1];
		envoi[0] = player;
		client.sendTCP(envoi);
	}

	/**
	 * Send to player in an array.
	 * 
	 * @param a
	 *            First player to send
	 * @param b
	 *            Second player to send
	 */
	public void sendPlayers(Player a, Player b) {
		envoi = new Player[2];
		envoi[0] = a;
		envoi[1] = b;
		client.sendTCP(envoi);
	}

	/**
	 * Put recu to null.
	 */
	public void setNull() {
		recu = null;
	}
}
