package com.example.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.ClientException;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.configuration.PersistentStoreConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.cache.Cache;

@SpringBootApplication
public class IgniteApplication implements CommandLineRunner {

    public static void main(String[] args) {
	    SpringApplication.run(IgniteApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

	IgniteConfiguration igniteConfiguration = new IgniteConfiguration();

	Ignite ignite = Ignition.start(igniteConfiguration);

	IgniteCache<Integer, Address> cache = ignite.getOrCreateCache("mytestcache");

	cache.put(1, new Address("John", 111));
	cache.put(2, new Address("Anna", 222));
	cache.put(3, new Address("George", 333));

	for (int i=1; i<=3; i++) {
	    Address s = cache.get(i);
	    System.out.println(s);
	}

    }

    class Address {

	private int num;
	private String name;

	public Address(String name, int num) {
	    this.name = name;
	    this.num = num;
	}

	@Override
	public String toString() {
	    return "Address{" + "name='" + name + '\'' + ", num=" + num + '}';
	}
    }

}


