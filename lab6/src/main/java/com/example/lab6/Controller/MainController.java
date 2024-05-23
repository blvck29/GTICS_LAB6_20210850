package com.example.lab6.Controller;

import com.example.lab6.Entity.Dispositivo;
import com.example.lab6.Repository.RepoDispositivo;
import com.example.lab6.Repository.RepoReserva;
import com.example.lab6.Repository.RepoUsuario;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class MainController {

    final RepoDispositivo repoDispositivo;
    final RepoUsuario repoUsuario;
    final RepoReserva repoReserva;

    public MainController(RepoDispositivo repoDispositivo, RepoUsuario repoUsuario, RepoReserva repoReserva) {
        this.repoDispositivo = repoDispositivo;
        this.repoUsuario = repoUsuario;
        this.repoReserva = repoReserva;
    }

    @GetMapping("/dispositivos")
    public String dispositivos(Model model){



        ArrayList<Dispositivo> listaDispositivos = new ArrayList<>();

        listaDispositivos = (ArrayList<Dispositivo>) repoDispositivo.findAll();

        model.addAttribute("listaDispositivos",listaDispositivos);
        return "lista_dispositivos";
    }

    @GetMapping("/reservas")
    public String listaReservas(){
        return "lista_reservas";
    }

}
