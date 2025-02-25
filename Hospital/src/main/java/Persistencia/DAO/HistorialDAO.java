/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.DAO;

import Negocio.DTO.ConsultaDTO;
import Persistencia.Conexion.IConexion;
import Persistencia.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author SDavidLedesma
 */
public class HistorialDAO {

    IConexion conexion;

    public HistorialDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    private static final Logger LOG = Logger.getLogger(HistorialDAO.class.getName());

 

    public List<ConsultaDTO> obtenerHistorial(int idPaciente) throws SQLException, PersistenciaException {
        List<ConsultaDTO> historial = new ArrayList<>();
        String sql = "SELECT idConsulta, fechaConsulta, diagnostico  FROM Consulta WHERE idCita = ?";

        try (Connection con = this.conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPaciente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ConsultaDTO h = new ConsultaDTO();
         //       h.setFechaConsulta(rs.getLocalDate("fechaConsulta"));
                h.setDiagnostico(rs.getString("diagnostico"));
                h.setTratamiento(rs.getString("tratamiento"));
                historial.add(h);
            }
        }
        return historial;
    }
}
