package fatec.poo.model;

public class Pessoa {
    private String cpf,nome,endereco,cidade,
                   uf,cep,ddd,telefone;

    public Pessoa(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public String getCep() {
        return cep;
    }

    public String getDdd() {
        return ddd;
    }

    public String getTelefone() {
        return telefone;
    }
    
    public static boolean validarCPF(String cpf){
        cpf = cpf.replace(".","");
        cpf = cpf.replace("-","");
        int firstDigit = 0;
        int secondDigit = 0;
        
        char[] arrCPF = new char[11];
        arrCPF = cpf.toCharArray();
        
        for(int i = 0; i < arrCPF.length-2; i++){
         firstDigit += Character.getNumericValue(arrCPF[i])* (i+1);
         secondDigit += Character.getNumericValue(arrCPF[i]) * (11-i);
        }
        firstDigit %= 11;
        
        if (firstDigit == 10) {
            firstDigit = 0;
        }
        
        secondDigit = ((secondDigit  + firstDigit *2) * 10) % 11;
        
        if (secondDigit == 10) {
            secondDigit = 0;
        }
        
        return firstDigit == Character.getNumericValue(arrCPF[9]) && secondDigit == Character.getNumericValue(arrCPF[10]);
    }
    
}
