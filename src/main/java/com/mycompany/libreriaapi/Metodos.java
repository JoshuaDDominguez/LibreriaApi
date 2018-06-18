package com.mycompany.libreriaapi;

import java.io.*;
import java.net.URISyntaxException;
import javax.swing.JOptionPane;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.RemoteAddCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

/**
 * Clase con los métodos para realizar las acciones de hacer commit, inicializar
 * repositorio, hacer push y clonar.
 *
 * @author Luis Fernando
 */
public class Metodos {

    /**
     * Metodo para incializar el repositorio. El JOptionPane pide la ruta.
     *
     */
    public void inicializarRepo() {
        InitCommand repositorio = new InitCommand();
        try {
            repositorio.setDirectory(new File(JOptionPane.showInputDialog("Ruta del reposiorio: "))).call();
        } catch (GitAPIException ex) {
            System.out.println("Error:" + ex);
        }
    }

    /**
     * Metodo que realiza un commit de la aplicacion de la cual enviemos la ruta
     * en el primer JOptionPane. En la ultima pide el mensaje de nuestro commit.
     */
    public void commitRepository() {

        try {
            FileRepositoryBuilder repositoryBuilder = new FileRepositoryBuilder();
            Repository repository = repositoryBuilder.setGitDir(new File(JOptionPane.showInputDialog("Ruta del repositorio")))
                    .readEnvironment()
                    .findGitDir()
                    .setMustExist(true)
                    .build();

            Git git = new Git(repository);
            AddCommand add = git.add();
            add.addFilepattern(JOptionPane.showInputDialog("Ruta del repositorio")).call();
            CommitCommand commit = git.commit();
            commit.setMessage(JOptionPane.showInputDialog("Mensaje del commit:")).call();
        } catch (IOException ex) {
            System.out.println("Error:" + ex);
        } catch (GitAPIException ex) {
            System.out.println("Error:" + ex);
        }
    }

    /**
     * Metodo que realiza un push para subir un repositorio. Pide el nombre, la
     * ruta y los datos de usuario.
     *
     * @throws IOException
     * @throws URISyntaxException
     */
    public void pushRepository() throws IOException, URISyntaxException {

        try {
            FileRepositoryBuilder repositoryBuilder = new FileRepositoryBuilder();
            Repository repository = repositoryBuilder.setGitDir(new File(JOptionPane.showInputDialog("Dime el nombre de tu repositorio: ")))
                    .readEnvironment()
                    .findGitDir()
                    .setMustExist(true)
                    .build();
            Git git = new Git(repository);
            RemoteAddCommand remoteAddCommand = git.remoteAdd();
            remoteAddCommand.setName("origin");
            remoteAddCommand.setUri(new URIish(JOptionPane.showInputDialog("Direcion del repositorio remoto")));
            remoteAddCommand.call();

            // Push y datos de usuario
            PushCommand pushCommand = git.push();
            pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(JOptionPane.showInputDialog("Nombre de usuario: "), JOptionPane.showInputDialog("Contraseña: ")));
            pushCommand.call();

        } catch (GitAPIException ex) {
        }
    }

    /**
     * Pide la url del proyecto remoto y luego el nombre que le queramos dar.
     */
    public void clonar() {
        try {
            Git.cloneRepository().setURI(JOptionPane.showInputDialog("Url del proyecto.")).setDirectory(new File(JOptionPane.showInputDialog("Nombre que le vas a dar al nuevo proyecto."))).call();
            JOptionPane.showMessageDialog(null, "Clonado hecho");

        } catch (GitAPIException ex) {

            JOptionPane.showMessageDialog(null, "Se ha producido un error.");
        }
    }

}
