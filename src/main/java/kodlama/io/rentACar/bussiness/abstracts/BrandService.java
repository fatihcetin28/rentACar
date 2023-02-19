package kodlama.io.rentACar.bussiness.abstracts;

import java.util.List;

import kodlama.io.rentACar.bussiness.requests.CreateBrandRequest;
import kodlama.io.rentACar.bussiness.requests.UpdateBrandRequest;
import kodlama.io.rentACar.bussiness.responses.GetAllBrandsReponse;
import kodlama.io.rentACar.bussiness.responses.GetByIdBrandResponse;

public interface BrandService {
	List<GetAllBrandsReponse> getAll();
	GetByIdBrandResponse getById(int id);
	void add(CreateBrandRequest createBrandRequest);
	void update(UpdateBrandRequest updateBrandRequest);
	void delete(int id);
}
