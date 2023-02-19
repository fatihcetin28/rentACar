package kodlama.io.rentACar.bussiness.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kodlama.io.rentACar.bussiness.abstracts.BrandService;
import kodlama.io.rentACar.bussiness.requests.CreateBrandRequest;
import kodlama.io.rentACar.bussiness.requests.UpdateBrandRequest;
import kodlama.io.rentACar.bussiness.responses.GetAllBrandsReponse;
import kodlama.io.rentACar.bussiness.responses.GetByIdBrandResponse;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.BrandRepository;
import kodlama.io.rentACar.entities.concretes.Brand;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service //Bu sınıf bir bussiness nesnesidir

public class BrandManager implements BrandService{

	private BrandRepository brandRepository;
	private ModelMapperService modelMapperService;
	
	/*
	 * @Autowired public BrandManager(BrandRepository brandRepository) { super();
	 * this.brandRepository = brandRepository; }
	 */


	@Override
	public List<GetAllBrandsReponse> getAll() {
		
		List<Brand> brands = brandRepository.findAll();
		
		List<GetAllBrandsReponse> brandsResponse = brands.stream()
				.map(brand -> this.modelMapperService.forResponse()
						.map(brand, GetAllBrandsReponse.class)).collect(Collectors.toList());
		
		//iş kuralları
		return brandsResponse;
	}


	@Override
	public void add(CreateBrandRequest createBrandRequest) {
		
		//Brand brand = new Brand();
		//brand.setName(createBrandRequest.getName());
		
		Brand brand = this.modelMapperService.forRequest()
				.map(createBrandRequest, Brand.class);
		
		
		this.brandRepository.save(brand);
		
	}


	@Override
	public GetByIdBrandResponse getById(int id) {
		Brand brand = this.brandRepository.findById(id).orElseThrow();

		GetByIdBrandResponse response = this.modelMapperService.forResponse()
				.map(brand, GetByIdBrandResponse.class);
		return response;
	}


	@Override
	public void update(UpdateBrandRequest updateBrandRequest) {
		Brand brand = this.modelMapperService.forRequest()
				.map(updateBrandRequest, Brand.class);
		this.brandRepository.save(brand);
	}


	@Override
	public void delete(int id) {

		this.brandRepository.deleteById(id);
		
	}

}
