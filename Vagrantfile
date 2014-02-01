# -*- mode: ruby -*-
# vi: set ft=ruby :

VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|

  config.vm.define "loadbalancer" do |loadbalancer|
    loadbalancer.vm.box = "precise32-playscale"
    loadbalancer.vm.box_url = "vagrant-builder/precise32-playscale.box"

    loadbalancer.vm.network :forwarded_port, guest: 80, host: 8080
    loadbalancer.vm.network :private_network, ip: "192.168.33.10"

    loadbalancer.vm.provider :virtualbox do |vb|
      vb.customize ["modifyvm", :id, "--memory", "768"]
    end

    loadbalancer.vm.provision "shell", path: "loadbalancer-bootstrap.sh"
  end

  config.vm.define "frontend1" do |frontend1|
    frontend1.vm.box = "precise32-playscale"
    frontend1.vm.box_url = "vagrant-builder/precise32-playscale.box"

    frontend1.vm.network :forwarded_port, guest: 9010, host: 9010
    frontend1.vm.network :private_network, ip: "192.168.33.20"

    frontend1.vm.provider :virtualbox do |vb|
      vb.customize ["modifyvm", :id, "--memory", "512"]
    end

    frontend1.vm.provision "shell", path: "frontend1-bootstrap.sh"
  end
  
  config.vm.define "frontend2" do |frontend2|
    frontend2.vm.box = "precise32-playscale"
    frontend2.vm.box_url = "vagrant-builder/precise32-playscale.box"

    frontend2.vm.network :forwarded_port, guest: 9010, host: 9011
    frontend2.vm.network :private_network, ip: "192.168.33.21"  

    frontend2.vm.provider :virtualbox do |vb|
      vb.customize ["modifyvm", :id, "--memory", "512"]
    end

    frontend2.vm.provision "shell", path: "frontend2-bootstrap.sh"
  end

  config.vm.define "cache" do |cache|
    cache.vm.box = "precise32-playscale"
    cache.vm.box_url = "vagrant-builder/precise32-playscale.box"

    cache.vm.network :forwarded_port, guest: 11211, host: 11211
    cache.vm.network :private_network, ip: "192.168.33.30"

    cache.vm.provider :virtualbox do |vb|
      vb.customize ["modifyvm", :id, "--memory", "768"]
    end
    
    cache.vm.provision "shell", path: "cache-bootstrap.sh"
  end


  config.vm.define "backend1" do |backend1|
    backend1.vm.box = "precise32-playscale"
    backend1.vm.box_url = "vagrant-builder/precise32-playscale.box"

    backend1.vm.network :forwarded_port, guest: 9012, host: 9013
    backend1.vm.network :private_network, ip: "192.168.33.40"  
    
    backend1.vm.provider :virtualbox do |vb|
      vb.customize ["modifyvm", :id, "--memory", "384"]
    end

    backend1.vm.provision "shell", path: "backend-bootstrap.sh"
  end
end
