package com.tech.pro.walker.api.models.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tech.pro.walker.api.models.entity.Grid;

public interface IGridDao extends JpaRepository<Grid, Long> {

	@Query("from Grid g where g.ip.id_ip = ?1 order by g.numero_plano")
	public List<Grid> findAllGridByIp(Long id_ip);
	
	@Query("from Grid g where g.id_grid =?1")
	public Grid fingGridById(Long id_grid);
	
	@Query("select count(1) from Grid g where g.numero_plano =?1 AND g.ip.id_ip =?2")
	public int findByGrid(String numero_plano, Long id_ip);
	
	@Query("from Grid g where g.numero_plano =?1 AND g.ip.id_ip =?2")
	public Grid findGridByIdGrid(String numero_plano , Long id_ip);
	
	@Modifying
	@Query(value="SELECT SUM(calcularPies (G.id_grid, G.total_pies)) AS pies ,W.id_walker, CONCAT(W.nombre, ' ', W.apellido_paterno ) AS walker FROM walkout.proyectos AS  PRO\r\n" + 
			"    INNER JOIN walkout.ips AS IP ON ( PRO.id_proyecto = IP.id_proyecto AND IP.estatus = 1  AND IP.qc = 3)\r\n" + 
			"    INNER JOIN walkout.grids AS G ON ( G.id_ip = IP.id_ip AND G.estatus = 1)\r\n" + 
			"    INNER JOIN walkout.asignacion AS A ON ( A.id_grid = G.id_grid )\r\n" + 
			"    RIGHT JOIN walkout.walkers AS W ON ( A.id_walker = W.id_walker AND W.estatus = 1)\r\n" + 
			"    RIGHT JOIN walkout.proyecto_walkers as PW ON ( PW.id_walker = W.id_walker )\r\n" + 
			"    WHERE PW.id_proyecto =?1 GROUP BY W.id_walker ORDER BY pies DESC ;", nativeQuery = true)
	public List<Object> rptGlobalProyecto(Long id_proyecto);
	
	
	@Modifying
	@Query(value="SELECT SUM(calcularPies (G.id_grid, G.total_pies)) AS pies ,W.id_walker, CONCAT(W.nombre, ' ', W.apellido_paterno ) AS walker FROM walkout.proyectos AS  PRO\r\n" + 
			"    INNER JOIN walkout.ips AS IP ON ( PRO.id_proyecto = IP.id_proyecto AND IP.estatus = 1 AND IP.qc = 3 AND CAST(IP.fecha_levantamiento AS DATE) =?1 )\r\n" + 
			"    INNER JOIN walkout.grids AS G ON ( G.id_ip = IP.id_ip AND G.estatus = 1)\r\n" + 
			"    INNER JOIN walkout.asignacion AS A ON ( A.id_grid = G.id_grid )\r\n" + 
			"    RIGHT JOIN walkout.walkers AS W ON ( A.id_walker = W.id_walker AND W.estatus = 1)\r\n" + 
			"    RIGHT JOIN walkout.proyecto_walkers as PW ON ( PW.id_walker = W.id_walker )\r\n" + 
			"    WHERE PW.id_proyecto =?2 GROUP BY W.id_walker ORDER BY pies DESC ;", nativeQuery = true)
	public List<Object> rptGlobalProyectoByDay(String date, Long id_proyecto);
	
	@Modifying
	@Query( value = "SELECT SUM(calcularPies (G.id_grid, G.total_pies)) AS pies ,W.id_walker, CONCAT(W.nombre, ' ', W.apellido_paterno ) AS walker FROM walkout.proyectos AS  PRO\r\n" + 
			"    INNER JOIN walkout.ips AS IP ON ( PRO.id_proyecto = IP.id_proyecto AND IP.estatus = 1 AND IP.qc = 3 AND (IP.fecha_levantamiento >=?1 AND IP.fecha_levantamiento <=?2))\r\n" + 
			"    INNER JOIN walkout.grids AS G ON ( G.id_ip = IP.id_ip AND G.estatus = 1)\r\n" + 
			"    INNER JOIN walkout.asignacion AS A ON ( A.id_grid = G.id_grid )\r\n" + 
			"    RIGHT JOIN walkout.walkers AS W ON ( A.id_walker = W.id_walker AND W.estatus = 1)\r\n" + 
			"    RIGHT JOIN walkout.proyecto_walkers as PW ON ( PW.id_walker = W.id_walker )\r\n" + 
			"    WHERE PW.id_proyecto =?3 GROUP BY W.id_walker ORDER BY pies DESC ;", nativeQuery = true)
	public List<Object> rptGlobalProyectoSemanal(String fecha_incio, String fecha_final, Long id_proyecto);
}
