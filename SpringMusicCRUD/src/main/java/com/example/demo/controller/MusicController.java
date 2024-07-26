package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Music;
import com.example.demo.form.MusicForm;
import com.example.demo.service.MusicService;



@Controller
public class MusicController {
	
	@Autowired
	MusicService service;
	
	@GetMapping("index")
	public String indexView() {
		return "index";
	}
	@PostMapping(value="menu" ,params="select")
	public String listView(Model model) {
		Iterable<Music> list = service.findAll();
		model.addAttribute("list", list);
		return "list";
	}
	@PostMapping(value="menu" ,params="insert")
	public String musicInputView() {
		return "music-input";
	}
	@PostMapping(value="menu" ,params="update")
	public String musicUpdateView(Model model) {
		Iterable<Music> list = service.findAll();
		model.addAttribute("list", list);
		return "music-update";
	}
	@PostMapping("insert")
	public String musicConfirmView(MusicForm f) {
		Music music = new Music(f.getSong_id(),
				f.getSong_name(),f.getSinger());
		System.out.println(music);
		service.insertMusic(music);
		return "music-inputcom";
	}
	@PostMapping("update")
	public String musicUpdatecomView(MusicForm f) {
		Music music = new Music(f.getSong_id(),
				f.getSong_name(),f.getSinger());
		service.updateMusic(music);
		return "music-updatecom";
	}
}