package edu.dataflow.core.job;
/*
 * // $Id$ //
 *
 * tGSF -- teikoku Grid Scheduling Framework
 *
 * Copyright (c) 2006-2009 by the
 *   Robotics Research Institute (Section Information Technology)
 *   at TU Dortmund University, Germany
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the
 *
 *   Free Software Foundation, Inc.,
 *   51 Franklin St, Fifth Floor,
 *   Boston, MA 02110, USA
 */
// $Id$

/*
 * "Teikoku Scheduling API" -- A Generic Scheduling API Framework
 *
 * Copyright (c) 2007 by the
 *   Robotics Research Institute (Information Technology Section)
 *   Dortmund University, Germany
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the
 *
 *   Free Software Foundation, Inc.,
 *   51 Franklin St, Fifth Floor,
 *   Boston, MA 02110, USA
 */




import java.util.Vector;
import edu.dataflow.core.util.FileSetting;


final public class SWFJob implements Job, Vertex{

	private Vector<String> predecessors;
	
	private Vector<String> successors;
	
	private String jid;

	private String asPath;
	
	private String asOptions;
	
	private Vector<FileSetting> in;
	
	private Vector<FileSetting> out;
	
	
	public SWFJob () {
		this.predecessors = new Vector<String>();
		this.successors = new Vector<String>();
		this.in	= new Vector<FileSetting>();
		this.out = new Vector<FileSetting>();
	}
	
	public void addInputFile(FileSetting fs) {
		in.add(fs);
	}
	
	public void addOutFile(FileSetting fs) {
		out.add(fs);
		
	}

		
	public void setJID(String jid) {
		this.jid = jid;
	}
	
	
	public void addPredecessor(String predecessor) {
		this.predecessors.add(predecessor); 
	}
	
	public void addSuccessor(String sucessor) {
		this.successors.add(sucessor); 
	}
	
	
	public void setActivationSetPath(String path) {
		this.asPath = path;
	}
	
	public void setActivationSetOpt(String options) {
		this.asOptions = options;
	}
	
	public String getJID() {
		return this.jid;
	}

	public Vector<String> getSuccesors() {
		return this.successors;
	}
	
	public Vector<FileSetting> getInputFiles() {
		return this.in;
	}
	
	public Vector<FileSetting> getOutputFiles() {
		return this.out;
	}
}
