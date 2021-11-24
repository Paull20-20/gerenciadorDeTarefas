
package br.com.bean2;


//import br.com.dao.DAO;
import br.com.entidade2.Pessoa;
import br.com.util2.ConectandoDb;


import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped; //define o tempo de sessão, no web.xml pode definir o tempo, atualmente está 30 minutos


@SessionScoped
@ManagedBean(name = "pessoaBean")


public class PessoaBean {
    
    //Aqui nos nossos atributos colocamos os nomes das informações que estaremos recebendo input pelo front end
    
    public Pessoa pessoa1 = new Pessoa();
    //lista para armazenar informações inseridas via input na web
    private List<Pessoa> pessoa = new ArrayList<>();
    private ConectandoDb conectandoDb = new ConectandoDb();

    public Pessoa getPessoa1() {
        return pessoa1;
    }

    public void setPessoa1(Pessoa pessoa1) {
        this.pessoa1 = pessoa1;
    }
    
    public List<Pessoa> getPessoa(){
        return pessoa;
    }
    
    /*
    public void setPessoa(List<Pessoa> pessoa){
        this.pessoa = pessoa;
    }
    */

   //método para adicionar as informações
    public void adicionar(){
        //pessoa.add(pessoa1);
        conectandoDb.inserir(pessoa1); //chamando método inserir da classe Conectandodb
        pessoa1 = new Pessoa();
    }

   public void listarTarefas(){
       pessoa = conectandoDb.listar();
   }
 
   //NÃO ESTÁ SENDO USADO!
   public void editar(){
       pessoa1 = new Pessoa();
       conectandoDb.editar(pessoa1);
       pessoa.add(pessoa1);
       
   }
   
   //Esse método faz os dados voltarem pro input e assim podemos editar, com isso cadastramos e atualizamos nossa lista!
   public void edicao(Pessoa p){
       pessoa1 = p;
   }
   
   public void delete2(){
       conectandoDb.delete(8); //Só exclui se passar o ID aqui
   }
   
   public void tarefaConcluida(){
       conectandoDb.finalizarTarefa(5); //Passar o id da tarefa aqui
   }
   
   
}
