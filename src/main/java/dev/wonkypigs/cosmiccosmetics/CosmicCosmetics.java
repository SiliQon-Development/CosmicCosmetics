package dev.wonkypigs.cosmiccosmetics;

import dev.wonkypigs.cosmiccosmetics.commands.*;
import dev.wonkypigs.cosmiccosmetics.listeners.*;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public final class CosmicCosmetics extends JavaPlugin {
    private Connection connection;
    public String host, database, username, password;
    public int port;
    public String prefix = ChatColor.translateAlternateColorCodes('&', "&8&l[&6CosmicCosmetics&8]&r ");

    ////////////////////////////////////////////////////////////////
    // Plugin startup logic
    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        registerCommands();
        registerListeners();
        mySqlSetup();
        getLogger().info("CosmicCosmetics has been enabled successfully!");
    }

    ////////////////////////////////////////////////////////////////
    // Plugin shutdown logic
    @Override
    public void onDisable() {
        getLogger().info("CosmicCosmetics has been disabled!");
    }

    ////////////////////////////////////////////////////////////////
    // Registering all plugin commands
    private void registerCommands() {
        Objects.requireNonNull(getCommand("cosmiccosmetics")).setExecutor(new CosmeticsMenu());
        getLogger().info("Commands successfully registered");
    }

    ////////////////////////////////////////////////////////////////
    // Registering all plugin listeners
    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new CosmeticsMenuHandler(), this);
        getLogger().info("Listeners successfully registered");
    }

    ////////////////////////////////////////////////////////////////
    // Getting values from config with color coding
    public String getConfigValue(String key) {
        String ans = getConfig().getString(key);
        return ChatColor.translateAlternateColorCodes('&', ans);
    }

    ////////////////////////////////////////////////////////////////
    // MySQL setup things
    public Connection getConnection() {
        return connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private void mySqlSetup() {
        host = getConfig().getString("database.host");
        port = getConfig().getInt("database.port");
        database = getConfig().getString("database.database");
        username = getConfig().getString("database.username");
        password = getConfig().getString("database.password");

        try{
            synchronized (this) {
                if (getConnection() != null && !getConnection().isClosed()) {
                    return;
                }

                Class.forName("com.mysql.jdbc.Driver");
                setConnection(DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password));

                getLogger().info(getConfigValue("mysql-connection-success"));

                getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS cosmic_cosmetics_data (UUID varchar(200), NAME varchar(200), ECO int, MUTED boolean, BANNED boolean, WARNINGS int, MUTE_REASON varchar(200), BAN_REASON varchar(200))").executeUpdate();
            }
        }catch(SQLException | ClassNotFoundException e){
            getLogger().severe(getConfigValue("mysql-connection-fail"));
            e.printStackTrace();
        }
    }
}
